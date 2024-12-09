using System;
using System.Collections.Generic;
using System.Linq;
using System.Numerics;
using System.Security.Claims;
using System.Text;
using NUnit.Framework;
using Project_Euler.Source;

namespace Project_Euler.Tests._000_099
{
    /*
Counting Sundays

Problem 19
You are given the following information, but you may prefer to do some research for yourself.

  1 Jan 1900 was a Monday.
  Thirty days has September,
  April, June and November.
  All the rest have thirty-one,
  Saving February alone,
  Which has twenty-eight, rain or shine.
  And on leap years, twenty-nine.
  A leap year occurs on any year evenly divisible by 4, but not on a century unless 
  it is divisible by 400.
  
  How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
*/
    [TestFixture()]
    public class TestQuestion0019
    {
        [Test]
        public void TestCountingSundays()
        {
            MyDateTime mdt = new MyDateTime();
            mdt.Weekday = Weekday.Monday;
            mdt.Day = 1;
            mdt.Month = 1;
            mdt.Year = 1900;

            Int32 countOfSundays = 0;

            while (!IsFinished(mdt))
            {
                if(IsStarting(mdt) &&
                   mdt.Day == 1 &&
                   mdt.Weekday == Weekday.Sunday)
                {
                    countOfSundays++;
                    Console.WriteLine(mdt);
                }

                mdt++;
            }

            Assert.That(countOfSundays,Is.EqualTo(171));
        }

        private bool IsStarting(MyDateTime mdt)
        {
            return (mdt.Year >= 1901);
        }
        private bool IsFinished(MyDateTime mdt)
        {
            return (mdt.Year >= 2001);
        }

        public enum Weekday
        {
            Monday = 0,
            Tuesday = 1,
            Wednesday = 2,
            Thursday = 3,
            Friday = 4,
            Saturday = 5,
            Sunday = 6
        }

        internal class MyDateTime
        {
            internal Int32 Day;
            internal Int32 Month;
            internal Int32 Year;
            internal Weekday Weekday;

            public override string ToString()
            {
                return String.Format("{0}, {1} {2} {3}", Weekday, Day, Month, Year);
            }

            public override bool Equals(object obj)
            {
                MyDateTime remoteMdt = (MyDateTime) obj;
                return (this.Weekday == remoteMdt.Weekday &&
                        this.Day == remoteMdt.Day &&
                        this.Month == remoteMdt.Month &&
                        this.Year == remoteMdt.Year);
            }
            public static MyDateTime operator ++(MyDateTime mdt)
            {
                mdt.Weekday = (Weekday) ((int)(mdt.Weekday + 1) % 7);

                // Increment days
                mdt.Day++;
                // Check for 30 day months
                if (mdt.Month == 4 ||
                    mdt.Month == 6 ||
                    mdt.Month == 11 ||
                    mdt.Month == 9)
                {
                    if (mdt.Day > 30)
                    {
                        mdt.Day = 1;
                        mdt.Month++;
                    }
                }
                else if(mdt.Month == 1 ||
                        mdt.Month == 3 ||
                        mdt.Month == 5 ||
                        mdt.Month == 7 ||
                        mdt.Month == 8 ||
                        mdt.Month == 10)
                {
                    if (mdt.Day > 31)
                    {
                        mdt.Day = 1;
                        mdt.Month++;
                    }
                }
                else if (mdt.Month == 12)
                {
                    if (mdt.Day > 31)
                    {
                        mdt.Day = 1;
                        mdt.Month = 1;
                        mdt.Year++;
                    }
                }
                else if (mdt.Month == 2)
                {
                    Int32 limit;
                    if (IsLeapYear(mdt.Year))
                        limit = 29;
                    else
                        limit = 28;

                    if (mdt.Day > limit)
                    {
                        mdt.Day = 1;
                        mdt.Month++;
                    }
                }
                return mdt;
            }

            public static bool IsLeapYear(Int32 year)
            {
                if ((year % 100) == 0)
                {
                    if ((year % 400) == 0)
                        return true;
                    else
                        return false;
                }

                if ((year % 4) == 0)
                {
                    return true;
                }

                return false;
            }
        }

        [TestCase(Weekday.Monday, 1, 1, 1900, Weekday.Tuesday, 2, 1, 1900)]
        [TestCase(Weekday.Monday, 28, 2, 2000, Weekday.Tuesday, 29, 2, 2000)]
        [TestCase(Weekday.Monday, 28, 2, 1901, Weekday.Tuesday, 1, 3, 1901)]
        public void TestPlusPlus(Weekday weekday, Int32 day, Int32 month, Int32 year,
            Weekday expectedWeekday, Int32 expectedDay, Int32 expectedMonth, Int32 expectedYear)
        {
            MyDateTime mdt = new MyDateTime() {Weekday = weekday, Day = day, Month = month, Year = year};
            MyDateTime expectedMdt = new MyDateTime()
            {
                Weekday = expectedWeekday,
                Day = expectedDay,
                Month = expectedMonth,
                Year = expectedYear
            };
            Assert.That(mdt++, Is.EqualTo(expectedMdt));
        }
        


        [TestCase(1900, false)]
        [TestCase(1901, false)]
        [TestCase(1902, false)]
        [TestCase(1903, false)]
        [TestCase(1904, true)]
        [TestCase(1905, false)]
        [TestCase(1906, false)]
        [TestCase(1996, true)]
        [TestCase(2000, true)]
        [TestCase(2001, false)]
        [TestCase(2002, false)]
        [TestCase(2003, false)]
        [TestCase(2004, true)]
        public void TestIsLeapYear(Int32 year, bool expected)
        {
            Assert.That(MyDateTime.IsLeapYear(year), Is.EqualTo(expected));
        }
    }
}
