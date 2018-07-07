package main

import (
        "fmt"
)

func main() {
        // bool
        a := true
        b := false
        fmt.Println("a:", a, "b:", b,
        "a && b:", a && b,
        "a || b:", a || b)

        var i int = 90
        fmt.Println("i:%+v", i)

        c1 := complex(5, 7)
        c2 := 8 + 7i
        fmt.Println(c1, " + ", c2, " = ", c1 + c2)
        fmt.Println(c1, " product ", c2, " = ", c1 * c2)

}
