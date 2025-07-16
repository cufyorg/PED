@file:Suppress("FunctionName")

package org.cufy.ped.filters

import org.cufy.bson.BsonDocumentBuilder
import org.cufy.bson.BsonMarker2
import org.cufy.bson.BsonRegExp
import org.cufy.bson.by
import org.cufy.mongodb.`$regex`
import org.cufy.ped.BsonFieldCodec

// https://www.mongodb.com/docs/manual/reference/operator/query/#evaluation

/* ============= ------------------ ============= */

/** https://www.mongodb.com/docs/manual/reference/operator/query/regex */
@BsonMarker2
context(_: /* Query */BsonDocumentBuilder)
infix fun BsonFieldCodec</* String */*>.`$regex`(regex: String) =
    this.name by { `$regex` by regex }

/** https://www.mongodb.com/docs/manual/reference/operator/query/regex */
@BsonMarker2
context(_: /* Query */BsonDocumentBuilder)
infix fun BsonFieldCodec</* String */*>.`$regex`(regex: BsonRegExp) =
    this.name by { `$regex` by regex }

/* ============= ------------------ ============= */
