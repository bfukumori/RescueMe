package br.com.fiap.rescueme

data class Shelter(
    val name: String,
    val address: String,
    val distance: String,
    val mapUrl: String // Para abrir no mapa
)