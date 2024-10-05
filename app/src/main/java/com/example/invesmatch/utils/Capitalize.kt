package com.example.invesmatch.utils


fun capitalizeString(input: String): String {
    return input.split(" ").joinToString(" ") {
        it.replaceFirstChar { char ->
            if (char.isLowerCase()) char.titlecase() else char.toString()
        }
    }
}