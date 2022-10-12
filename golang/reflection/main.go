package main

import (
	"dz/reflection/dr"
	"fmt"
)

func main() {
	deepEqual()
}

func deepEqual() {

	myFloat1, myFloat2 := 10.0, 10.0

	mt1 := dr.MyType{IntField: 10, StrField: "str1", PtrField: &myFloat1, SliceField: []int{1}}
	mt2 := dr.MyType{IntField: 10, StrField: "str1", PtrField: &myFloat2, SliceField: []int{1}}

	// b := mt1 == mt2 // compilation error
	fmt.Printf("mt1.IsEqual(mt2) : %v\n", mt1.IsEqual(mt2))
}
