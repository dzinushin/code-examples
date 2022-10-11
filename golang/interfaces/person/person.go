package person

import "fmt"

type Person struct {
	Name string
}

func (p Person) Work(tasks []string) string {
	result := "Person: " + p.Name + " work:"
	for _, task := range tasks {
		result += "\nI do: " + task
	}
	return result
}

func (p Person) String() string {
	return fmt.Sprintf("Person: %s", p.Name)
}
