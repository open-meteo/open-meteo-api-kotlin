package com.openmeteo.api.common.query

import com.openmeteo.api.common.Coordinate

@Deprecated(
    "Hardcoded Cities are deprecated: use the geocoding API instead!",
    ReplaceWith(
        "GeocodingSearch(...) { count = 1 }.getOrThrow().results[0]",
        "com.openmeteo.api.GeocodingSearch"
    ),
    DeprecationLevel.WARNING
)
enum class City(
    override val latitude: Float,
    override val longitude: Float,
) : Coordinate {
    Berlin(52.5235f, 13.4115f),
    Paris(48.8567f, 2.3510f),
    London(51.5002f, -0.1262f),
    Madrid(40.4167f, -3.7033f),
    Vienna(48.2092f, 16.3728f),
    Brussels(50.8371f, 4.3676f),
    Moscow(55.7558f, 37.6176f),
    Sofia(42.7105f, 23.3238f),
    Copenhagen(55.6763f, 12.5681f),
    Athens(37.9792f, 23.7166f),
    Budapest(47.4984f, 19.0408f),
    Reykjavik(64.1353f, -21.8952f),
    Dublin(53.3441f, -6.2675f),
    Rome(41.8955f, 12.4823f),
    Amsterdam(52.3738f, 4.8910f),
    Oslo(59.9138f, 10.7387f),
    Warsaw(52.2297f, 21.0122f),
    Lisabon(38.7072f, -9.1355f),
    Bern(46.9480f, 7.4481f),
    Kiev(50.4422f, 30.5367f),
    Stockholm(59.3328f, 18.0645f),
    Washington(38.8921f, -77.0241f),
    NewYork(40.71f, -74.01f),
    Sacramento(38.5737f, -121.4871f),
    LosAngeles(34.05f, -118.24f),
    Chicago(41.85f, -87.65f),
    Houston(29.76f, -95.36f),
    Phoenix(33.45f, -112.07f),
    Philadelphia(39.95f, -75.16f),
    Vancouver(49.25f, -123.12f),
    Ottawa(45.4235f, -75.6979f),
    BuenosAires(-34.6118f, -58.4173f),
    Brasilia(-15.7801f, -47.9292f),
    Santiago(-33.4691f, -70.6420f),
    Bogota(4.6473f, -74.0962f),
    CiudadDeMexico(19.4271f, -99.1276f),
    Asuncion(-25.3005f, -57.6362f),
    Lima(-12.0931f, -77.0465f),
    Montevideo(-34.8941f, -56.0675f),
    Kabul(34.5155f, 69.1952f),
    Dhaka(23.7106f, 90.3978f),
    Peking(39.9056f, 116.3958f),
    Tiflis(41.7010f, 44.7930f),
    NewDelhi(28.6353f, 77.2250f),
    Jakarta(-6.1862f, 106.8063f),
    Teheran(35.7061f, 51.4358f),
    Baghdad(33.3157f, 44.3922f),
    Jerusalem(31.7857f, 35.2007f),
    Tokyo(35.6785f, 139.6823f),
    KualaLumpur(3.1502f, 101.7077f),
    UlanBator(47.9138f, 106.9220f),
    Kathmandu(27.7058f, 85.3157f),
    Singapore(1.2894f, 103.8500f),
    Seoul(37.5139f, 126.9828f),
    Ankara(39.9439f, 32.8560f),
    AbuDhabi(24.4764f, 54.3705f),
    Algiers(36.7755f, 3.0597f),
    Luanda(-8.8159f, 13.2306f),
    Cairo(30.0571f, 31.2272f),
    Nairobi(-1.2762f, 36.7965f),
    Tripoli(32.8830f, 13.1897f),
    Windhoek(-22.5749f, 17.0805f),
    Pretoria(-25.7463f, 28.1876f),
    Canberra(-35.2820f, 149.1286f),
    Wellington(-41.2865f, 174.7762f);

    /**
     * Coordinates bundled in a [Pair]
     */
    val coordinates get() = latitude to longitude
}
