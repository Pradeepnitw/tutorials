package main

import (
        "fmt"
)


func main() {
        var a [3]int // a == nil
        _ = a
        a = [3]int{12, 78, 50}
        a = [...]int{12, 78, 50}
        d := []int{12, 78, 50} // Creates a slice
        _ = d
        a = [3]int{12}  // [12, 0, 0]
        e := [...]string{"USA", "China", "India", "Germany", "France"}
        b := e // deep copy
        b[0] = "Singapore" // Does not impact a
        // Function calls also does deep copy
        c := e[:] // shallow copy using slice
        _ = c
        // len(c) is the sliced length cap(c) is length of a

        f := [...]float64{67.7, 89.8, 21, 78}
        for i, v := range f {//range returns both the index and value
                fmt.Printf("%d the element of a is %.2f\n", i, v)
        }

        g := make([]int, 5, 5) // make(type, len, cap)
        _ = g
        // Can i make an arry using a = make([...]int, 5, 5) ???
}
