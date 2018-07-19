package main

import (
        "fmt"
)

func main () {
        aMap := make(map[string]int)
        _ = aMap

        var personSalary map[string]int
        if personSalary == nil {
                fmt.Println("map is nil, initializing personSalary")
                personSalary = make(map[string]int)
        }
        personSalary = map[string]int {
                "steve": 12000,
                "jamie": 15000,
        }
        personSalary["mike"] = 9000
        fmt.Println("personSalary map contents:", personSalary)
        steveSalary, ok := personSalary["steve"]
        if ok {
                fmt.Println("Steve earns", steveSalary)
        }

        fmt.Println("All values of a map")
        for k, v := range personSalary {
                fmt.Printf("personSalary[%s] = %d\n", k, v)
        }

        fmt.Println("Map before deletion", personSalary)
        delete(personSalary, "steve")
        fmt.Println("Map after deletion", personSalary)
}
