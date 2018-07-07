// Copied from $HOME/go/src/hello
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

        var age_infered = 29
        fmt.Println("my age is", age, reflect.TypeOf(age_infered))

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
}
