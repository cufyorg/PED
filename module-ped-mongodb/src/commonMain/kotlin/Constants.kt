package org.cufy.ped

infix fun BsonFieldCodec<*>.dot(other: String) =
    "${this.name}.$other"

infix fun BsonFieldCodec<*>.dollar(other: String) =
    "${this.name}.$.$other"

infix fun <T> String.dot(other: BsonFieldCodec<T>) =
    FieldCodec("$this.${other.name}", other.codec)

infix fun <T> String.dollar(other: BsonFieldCodec<T>) =
    FieldCodec("${this}.$.${other.name}", other.codec)

infix fun <T> BsonFieldCodec<*>.dot(other: BsonFieldCodec<T>) =
    FieldCodec("${this.name}.${other.name}", other.codec)

infix fun <T> BsonFieldCodec<*>.dollar(other: BsonFieldCodec<T>) =
    FieldCodec("${this.name}.$.${other.name}", other.codec)
