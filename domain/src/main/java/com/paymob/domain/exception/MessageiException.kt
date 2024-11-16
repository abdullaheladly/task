package com.paymob.domain.exception



/**
 * Exception when communicating with the remote api.
 */
data class MessageException(override val message:String) : Exception()
