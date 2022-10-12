package dr

import "reflect"

type MyType struct {
	IntField   int
	StrField   string
	PtrField   *float64
	SliceField []int
}

func (mt MyType) IsEqual(other MyType) bool {
	return reflect.DeepEqual(mt, other)
}
