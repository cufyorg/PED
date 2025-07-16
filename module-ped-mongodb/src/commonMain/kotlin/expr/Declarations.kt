@file:OptIn(ExperimentalMongodbApi::class)
@file:Suppress("FunctionName")

package org.cufy.ped.expr

import org.cufy.bson.BsonDocumentBuilder
import org.cufy.bson.BsonMarker2
import org.cufy.bson.bson
import org.cufy.bson.by
import org.cufy.mongodb.ExperimentalMongodbApi
import org.cufy.mongodb.expr.Expr
import org.cufy.mongodb.expr.Expr._Element
import org.cufy.ped.BsonFieldCodec

/* ============= ------------------ ============= */

@BsonMarker2
context(builder: BsonDocumentBuilder)
infix fun BsonFieldCodec<*>.by(expr: Expr<*>) =
    this.name by expr.element

/* ============= ------------------ ============= */

@BsonMarker2
fun <T : _Element> `$`(path: BsonFieldCodec<*>) = Expr<T>("$${path.name}".bson)

/* ============= ------------------ ============= */
