package company

import "fmt"

// Worker — интерфейс работника компании
type Worker interface {
	Work(tasks []string) string
}

type Company struct {
	Name     string
	personal []Worker
}

func (c *Company) Hire(newbie Worker) {
	c.personal = append(c.personal, newbie)
}

func (c Company) Process(id int, tasks []string) (res string) {
	return c.personal[id].Work(tasks)
}

func (c Company) String() string {
	return fmt.Sprintf("company: %s number employers: %d", c.Name, len(c.personal))
}
