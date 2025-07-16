@file:Suppress("FunctionName")

package org.cufy.ped.filters

import org.cufy.bson.*
import org.cufy.mongodb.`$bitsAllClear`
import org.cufy.mongodb.`$bitsAllSet`
import org.cufy.mongodb.`$bitsAnyClear`
import org.cufy.mongodb.`$bitsAnySet`
import org.cufy.ped.BsonFieldCodec

// https://www.mongodb.com/docs/manual/reference/operator/query/#bitwise

/* ============= ------------------ ============= */

/** https://www.mongodb.com/docs/manual/reference/operator/query/bitsAllClear */
@BsonMarker2
context(_: /* Query */BsonDocumentBuilder)
infix fun BsonFieldCodec</* Number */*>.`$bitsAllClear`(bitMask: Long) =
    this.name by { `$bitsAllClear` by bitMask }

/** https://www.mongodb.com/docs/manual/reference/operator/query/bitsAllClear */
@BsonMarker2
context(_: /* Query */BsonDocumentBuilder)
infix fun BsonFieldCodec</* Number */*>.`$bitsAllClear`(bitMask: BsonBinary) =
    this.name by { `$bitsAllClear` by bitMask }

/** https://www.mongodb.com/docs/manual/reference/operator/query/bitsAllClear */
@BsonMarker2
context(_: /* Query */BsonDocumentBuilder)
infix fun BsonFieldCodec</* Number */*>.`$bitsAllClear`(positions: List<Int>) =
    this.name by { `$bitsAllClear` by array { positions.forEach { contextOf().add(it.bson) } } }

/* ============= ------------------ ============= */

/** https://www.mongodb.com/docs/manual/reference/operator/query/bitsAllSet */
@BsonMarker2
context(_: /* Query */BsonDocumentBuilder)
infix fun BsonFieldCodec</* Number */*>.`$bitsAllSet`(bitMask: Long) =
    this.name by { `$bitsAllSet` by bitMask }

/** https://www.mongodb.com/docs/manual/reference/operator/query/bitsAllSet */
@BsonMarker2
context(_: /* Query */BsonDocumentBuilder)
infix fun BsonFieldCodec</* Number */*>.`$bitsAllSet`(bitMask: BsonBinary) =
    this.name by { `$bitsAllSet` by bitMask }

/** https://www.mongodb.com/docs/manual/reference/operator/query/bitsAllSet */
@BsonMarker2
context(_: /* Query */BsonDocumentBuilder)
infix fun BsonFieldCodec</* Number */*>.`$bitsAllSet`(positions: List<Int>) =
    this.name by { `$bitsAllSet` by array { positions.forEach { contextOf().add(it.bson) } } }

/* ============= ------------------ ============= */

/** https://www.mongodb.com/docs/manual/reference/operator/query/bitsAnyClear */
@BsonMarker2
context(_: /* Query */BsonDocumentBuilder)
infix fun BsonFieldCodec</* Number */*>.`$bitsAnyClear`(bitMask: Long) =
    this.name by { `$bitsAnyClear` by bitMask }

/** https://www.mongodb.com/docs/manual/reference/operator/query/bitsAnyClear */
@BsonMarker2
context(_: /* Query */BsonDocumentBuilder)
infix fun BsonFieldCodec</* Number */*>.`$bitsAnyClear`(bitMask: BsonBinary) =
    this.name by { `$bitsAnyClear` by bitMask }

/** https://www.mongodb.com/docs/manual/reference/operator/query/bitsAnyClear */
@BsonMarker2
context(_: /* Query */BsonDocumentBuilder)
infix fun BsonFieldCodec</* Number */*>.`$bitsAnyClear`(positions: List<Int>) =
    this.name by { `$bitsAnyClear` by array { positions.forEach { contextOf().add(it.bson) } } }

/* ============= ------------------ ============= */

/** https://www.mongodb.com/docs/manual/reference/operator/query/bitsAnySet */
@BsonMarker2
context(_: /* Query */BsonDocumentBuilder)
infix fun BsonFieldCodec</* Number */*>.`$bitsAnySet`(bitMask: Long) =
    this.name by { `$bitsAnySet` by bitMask }

/** https://www.mongodb.com/docs/manual/reference/operator/query/bitsAnySet */
@BsonMarker2
context(_: /* Query */BsonDocumentBuilder)
infix fun BsonFieldCodec</* Number */*>.`$bitsAnySet`(bitMask: BsonBinary) =
    this.name by { `$bitsAnySet` by bitMask }

/** https://www.mongodb.com/docs/manual/reference/operator/query/bitsAnySet */
@BsonMarker2
context(_: /* Query */BsonDocumentBuilder)
infix fun BsonFieldCodec</* Number */*>.`$bitsAnySet`(positions: List<Int>) =
    this.name by { `$bitsAnySet` by array { positions.forEach { contextOf().add(it.bson) } } }

/* ============= ------------------ ============= */
