package main

import (
	"fmt"
	"itf/company"
	"itf/person"
	"itf/robot"
)

// check that Robot implements Worker interface
var _ company.Worker = (*robot.Robot)(nil)

func main() {

	p := person.Person{Name: "Person"}
	r := &robot.Robot{Id: "001", Model: "ZZZ01"}
	c := company.Company{Name: "DZ"}

	c.Hire(p)
	c.Hire(r)
	fmt.Println(c)

	workForMen := []string{"manWork1", "manWork2"}
	workForRobot := []string{"roboWork1", "roboWork2"}

	manRes := c.Process(0, workForMen)
	roboRes := c.Process(1, workForRobot)

	fmt.Println(manRes)
	fmt.Println(roboRes)

	p2 := person.Person{
		Name: "Second",
	}

	var worker2 company.Worker = &p2
	c.Hire(worker2)
	println()
	println(c.String())
}
