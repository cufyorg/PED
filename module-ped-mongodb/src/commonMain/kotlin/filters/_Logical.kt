@file:Suppress("FunctionName")

package org.cufy.ped.filters

import org.cufy.bson.BsonDocumentBuilder
import org.cufy.bson.BsonMarker2
import org.cufy.bson.by
import org.cufy.mongodb.`$not`
import org.cufy.ped.BsonDocument
import org.cufy.ped.BsonDocumentCodecBlock
import org.cufy.ped.BsonFieldCodec

// https://www.mongodb.com/docs/manual/reference/operator/query/#logical

/* ============= ------------------ ============= */

/** https://www.mongodb.com/docs/manual/reference/operator/query/not */
@BsonMarker2
context(_: /* Query */BsonDocumentBuilder)
infix fun <T> BsonFieldCodec<T>.`$not`(operatorExpression: /* Operator */BsonDocumentCodecBlock<T>) =
    this.name by { `$not` by BsonDocument(this.codec, operatorExpression) }

/* ============= ------------------ ============= */
