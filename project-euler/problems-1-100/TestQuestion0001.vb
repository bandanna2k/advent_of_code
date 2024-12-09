Imports NUnit.Framework

<TestFixture>
Public Class TestQuestion0001

    ''' <summary>
    ''' Multiples of 3 and 5
    ''' 
    ''' Problem 1
    ''' 
    ''' If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
    ''' Find the sum of all the multiples of 3 or 5 below 1000.
    ''' </summary>
    <Timeout(60 * 1000)>
    <TestCase(10, 23)>
    <TestCase(1000, 233168)>
    Public Sub TestQ1(threshold As Int64,
                      expected As Int64)
        Assert.That(GetMultiplesOf3(threshold), [Is].EqualTo(expected))
    End Sub

    Private Function GetMultiplesOf3(threshold As Int64) As Int64
        Dim result As Int64 = 0
        For i As Int64 = 1 To (threshold - 1)
            If HelperFunctions.IsMultipleOf3(i) OrElse
               HelperFunctions.IsMultipleOf5(i) Then
                'Console.WriteLine(String.Format("{0} is a multiple.", i))
                result = result + i
            End If
        Next
        Return result
    End Function

End Class
