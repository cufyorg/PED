package org.cufy.ped

import org.cufy.bson.BsonDocument
import org.cufy.bson.by

@ExperimentalPEDApi
fun <K, V> buildMapCodec(
    decodeKey: (String) -> K,
    valueCodec: BsonCodec<V>,
): BsonCodec<Map<K, V>> {
    return buildMapCodec(
        keyCodec = buildStringCodec(decodeKey),
        valueCodec = valueCodec,
    )
}

@ExperimentalPEDApi
fun <K, V> buildMapCodec(
    keyCodec: Codec<K, String>,
    valueCodec: BsonCodec<V>,
): BsonCodec<Map<K, V>> {
    return Codec {
        encodeCatching { it: Map<K, V> ->
            BsonDocument {
                for ((key, value) in it) {
                    val encodedKey = key encode keyCodec
                    val encodedValue = value encode valueCodec
                    encodedKey by encodedValue
                }
            }
        }
        decodeCatching { it: BsonDocument ->
            buildMap(capacity = it.size) {
                for ((key, value) in it) {
                    val decodedKey = key decode keyCodec
                    val decodedValue = value decode valueCodec
                    put(decodedKey, decodedValue)
                }
            }
        }
    }
}
