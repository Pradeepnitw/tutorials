package main

import (
        "fmt"
        "reflect"
        "math"
)

func main() {
        // fmt.Printf("hello, world\n")
        
        // Part 3 variables
        // var age int
        // var age int = 29
        age := 29
        fmt.Println("my age is", age)

        var ageInfered = 29
        fmt.Println("my age is", age, reflect.TypeOf(ageInfered))

        var width, hei int = 100, 50
        fmt.Println("width is", width, "height is", hei)

        var (
                name = "fiona"
                age2 = 29
        )
        fmt.Println("my name is", name, ", age is", age2)

        name3, age3 := "fiona", 29
        fmt.Println("my name is", name3, ", age is", age3)

        a, b := 123.4, 532.2
        c := math.Min(a, b)
        fmt.Println("a =", a, ", b =", b, ", min value is", c)

        const d = 55
        // d = 89
        // re-assignment of const is not allowed
        const noType = "noType is an untyped string constant."
        const typedString string = "typedString is a type string constant."
        // const can be untyped but variable cannot.

        var defaultName = "Sam"
        type myString string  // define our own type
        var customName myString = "customeName is type myString"
        // customeName = defaultName
        // Cannot assign a no type variable to a myString type variableA
        fmt.Printf("defaultName type:%T, value:%v\ncustomName type:%T, value:%v", defaultName, defaultName, customName, customName)

        const e = 5 // untyped int const
        var intVar int = e
        var int32Var int32 = e
        var float64Var float64 = e
        var complex64Var complex64 = e
        fmt.Println("intVar",intVar, "\nint32Var", int32Var, "\nfloat64Var", float64Var, "\ncomplex64Var",complex64Var)
}
