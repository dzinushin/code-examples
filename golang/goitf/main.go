package main

import (
	"errors"
	"fmt"
	"goitf/randbyte"
	"io"
	"strings"
	"time"
)

func main() {
	readerString()
	readerRand()
}

func readerString() {
	s := "Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor Mordor"

	r := strings.NewReader(s)

	b := make([]byte, 64)

	fmt.Printf("len(b): %d\n", len(b))

	for {
		n, err := r.Read(b)
		if n > 0 {
			fmt.Printf("Readed (%d): %v\n", n, b)
		}

		if err != nil {
			if errors.Is(err, io.EOF) {
				break
			}
			fmt.Printf("error: %v\n", err)
			break
		}
	}
}

func readerRand() {
	// создаём генератор случайных чисел
	generator := randbyte.New(time.Now().UnixNano()) // в качестве затравки передаём ему текущее время, и при каждом запуске оно будет разным.

	buf := make([]byte, 16)

	for i := 0; i < 5; i++ {
		n, _ := generator.Read(buf) // единственный доступный метод, но он нам и нужен.
		fmt.Printf("Generate bytes: %v size(%d)\n", buf, n)
	}
}
