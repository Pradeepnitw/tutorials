package main

import (
        "fmt"
        "hello/geometry/rectangle"
        "log"
)

var recLen, recWid float64 = 6, 7

func init() {
        fmt.Println("Geometry package initizalizing... ")
        if recLen < 0 {
                log.Fatal("length is less than zero")
        }
        if recWid < 0 {
                log.Fatal("width is less than zero")
        }
}

func main() {
        fmt.Println("Geometrical shape properties")
        fmt.Printf("Area of rectangle %.2f\n", rectangle.Area(recLen, recWid))
        fmt.Printf("Diagonal of the rectangle %.2f\n", rectangle.Diagonal(recLen, recWid))
}
