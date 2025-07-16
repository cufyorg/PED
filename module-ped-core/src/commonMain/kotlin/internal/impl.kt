package org.cufy.ped.internal

import org.cufy.ped.Codec
import org.cufy.ped.FieldCodec

class FieldCodecImpl<I, O>(
    override val name: String,
    override val codec: Codec<I, O>
) : FieldCodec<I, O>, Codec<I, O> by codec

abstract class AbstractWrapperCodec<I, O>(
    val codec: Codec<I, O>,
) : Codec<I, O> by codec
