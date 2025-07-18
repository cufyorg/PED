@file:OptIn(ExperimentalMongodbApi::class)
@file:Suppress("FunctionName")

package org.cufy.ped.expr

import org.cufy.bson.*
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

@BsonMarker4
fun <T : _Element> `$`(path: BsonFieldCodec<*>) = Expr<T>("$${path.name}".bson)

@BsonMarker4
fun <T : _Element> `$$`(path: BsonFieldCodec<*>) = Expr<T>("$$${path.name}".bson)

/* ============= ------------------ ============= */
