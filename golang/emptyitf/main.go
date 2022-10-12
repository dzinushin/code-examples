package main

import (
	"fmt"
	"strconv"
)

type Stringer interface {
	String() string
}

type MyStringer struct {
}

func (s *MyStringer) String() string {
	return "..."
}

func main() {
	passAnyType(10)

	Printf(10)
	Printf("hello")

	ItfIsRefType()
}

func passAnyType(v interface{}) {
	i, ok := v.(int)
	if ok {
		println(i)
		var intVar int
		intVar = i
		println(intVar)
	}
}

func Printf(v interface{}) {

	switch v2 := v.(type) {
	case nil:
		fmt.Println("Это nil")
	case int:
		fmt.Println("Это число " + strconv.Itoa(v2))
	case string:
		fmt.Println("Это строка " + v2)
	case Stringer:
		fmt.Println("Это тип, реализующий Stringer, " + v2.String())
	default:
		fmt.Println("Неизвестный тип")
	}
}

func retStringer() Stringer {
	var ms *MyStringer // nil

	return ms
}

func ItfIsRefType() {
	var is Stringer

	fmt.Printf("Interface is reference type and can be nil: %v %T\n", is, is)

	fmt.Printf("Gotcha with itf %v\n", retStringer())
}
