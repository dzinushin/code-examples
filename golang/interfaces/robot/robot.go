package robot

import "fmt"

type Robot struct {
	Id        string
	Model     string
	WorkCount int
}

func (r Robot) String() string {
	return fmt.Sprintf("Robot Model: %s Id: %s", r.Model, r.Id)
}

func (r *Robot) Work(tasks []string) string {
	result := "Robot: " + r.Id + "/" + r.Model + " work:"
	for _, task := range tasks {
		result += "\nI do: " + task
		r.WorkCount++
	}
	return result
}
