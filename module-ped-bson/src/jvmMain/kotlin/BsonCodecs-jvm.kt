/*
 *	Copyright 2023 cufy.org and meemer.com
 *
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *
 *	    http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 */
package org.cufy.ped

import org.cufy.bson.BsonDateTime
import org.cufy.bson.BsonDecimal128
import org.cufy.bson.Decimal128
import java.math.BigDecimal
import java.util.*

/* ============= ------------------ ============= */

/**
 * The codec for [BigDecimal] and [BsonDecimal128].
 *
 * @since 2.0.0
 */
object BsonBigDecimalCodec : BsonCodec<BigDecimal> {
    override fun encode(value: Any?) =
        tryInlineCodec(value) { it: BigDecimal ->
            Result.success(BsonDecimal128(Decimal128(it)))
        }

    override fun decode(value: Any?) =
        tryInlineCodec(value) { it: BsonDecimal128 ->
            Result.success(it.value.toBigDecimal())
        }
}

/* ============= ------------------ ============= */

/**
 * The codec for [Date] and [BsonDateTime].
 *
 * @since 2.0.0
 */
object BsonDateCodec : BsonCodec<Date> {
    override fun encode(value: Any?) =
        tryInlineCodec(value) { it: Date ->
            Result.success(BsonDateTime(it))
        }

    override fun decode(value: Any?) =
        tryInlineCodec(value) { it: BsonDateTime ->
            Result.success(Date(it.value))
        }
}

/* ============= ------------------ ============= */
