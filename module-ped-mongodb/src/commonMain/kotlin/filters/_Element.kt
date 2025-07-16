@file:Suppress("FunctionName")

package org.cufy.ped.filters

import org.cufy.bson.*
import org.cufy.mongodb.`$exists`
import org.cufy.mongodb.`$type`
import org.cufy.ped.BsonFieldCodec

// https://www.mongodb.com/docs/manual/reference/operator/query/#element

/* ============= ------------------ ============= */

/** https://www.mongodb.com/docs/manual/reference/operator/query/exists */
@BsonMarker2
context(_: /* Query */BsonDocumentBuilder)
infix fun BsonFieldCodec<*>.`$exists`(value: Boolean) =
    this.name by { `$exists` by value }

/* ============= ------------------ ============= */

/** https://www.mongodb.com/docs/manual/reference/operator/query/type */
@BsonMarker2
context(_: /* Query */BsonDocumentBuilder)
infix fun BsonFieldCodec<*>.`$type`(type: Int) =
    this.name by { `$type` by type }

/** https://www.mongodb.com/docs/manual/reference/operator/query/type */
@BsonMarker2
context(_: /* Query */BsonDocumentBuilder)
infix fun BsonFieldCodec<*>.`$type`(type: String) =
    this.name by { `$type` by type }

/** https://www.mongodb.com/docs/manual/reference/operator/query/type */
@BsonMarker2
@JvmName($$"BsonFieldCodec<*>_$type_List<Int>")
context(_: /* Query */BsonDocumentBuilder)
infix fun BsonFieldCodec<*>.`$type`(types: List<Int>) =
    this.name by { `$type` by array { types.forEach { contextOf().add(it.bson) } } }

/** https://www.mongodb.com/docs/manual/reference/operator/query/type */
@BsonMarker2
@JvmName($$"BsonFieldCodec<*>_$type_List<String>")
context(_: /* Query */BsonDocumentBuilder)
infix fun BsonFieldCodec<*>.`$type`(types: List<String>) =
    this.name by { `$type` by array { types.forEach { contextOf().add(it.bson) } } }

/* ============= ------------------ ============= */
