Imports NUnit.Framework

<TestFixture>
Public Class HelperFunctions

#Region "Count All Digits"
    Public Shared Function CountAllDigits(value As Int64) As Int64
        Dim count As Int64 = 0
        Dim s As String = Convert.ToString(value)

        For Each c As Char In s.ToCharArray()
            count = count + Int32.Parse(c)
        Next
        Return count
    End Function

    <TestCase(0, 0), TestCase(1, 1), TestCase(9, 9), TestCase(10, 1)>
    <TestCase(10, 1), TestCase(19, 10), TestCase(99, 18), TestCase(1234567890, 45)>
    Public Sub TestCountAllDigits(value As Int64,
                                  expected As Int64)
        Assert.That(CountAllDigits(value), [Is].EqualTo(expected))
    End Sub
#End Region

#Region "Is Multiple"
    Public Shared Function IsMultipleOf3(value As Int64) As Boolean
        Return 0 = (HelperFunctions.CountAllDigits(value) Mod 3)
    End Function
    <TestCase(0, True), TestCase(3, True), TestCase(6, True), TestCase(9, True)>
    <TestCase(1, False), TestCase(4, False), TestCase(7, False)>
    <TestCase(102, True), TestCase(333, True), TestCase(963, True)>
    <TestCase(3 * 3 * 3 * 3, True)>
    <TestCase((3 * 3 * 3 * 3) + 1, False)>
    <TestCase(1234567890, True)>
    Public Sub TestIsMultipleOf3(value As Int64,
                                 expected As Boolean)
        Assert.That(IsMultipleOf3(value), [Is].EqualTo(expected))
    End Sub
    Public Shared Function IsMultipleOf5(value As Int64) As Boolean
        Dim s As String = Convert.ToString(value)
        If s.EndsWith("0") Then Return True
        If s.EndsWith("5") Then Return True
        Return False
    End Function
    <TestCase(0, True), TestCase(5, True), TestCase(10, True), TestCase(1055, True), TestCase(1000, True)>
    <TestCase(1, False), TestCase(22, False), TestCase(333, False), TestCase(4444, False)>
    <TestCase(666666, False), TestCase(7777777, False), TestCase(88888888, False), TestCase(999999999, False)>
    Public Sub TestIsMultipleOf5(value As Int64,
                                 expected As Boolean)
        Assert.That(IsMultipleOf5(value), [Is].EqualTo(expected))
    End Sub
#End Region

End Class
