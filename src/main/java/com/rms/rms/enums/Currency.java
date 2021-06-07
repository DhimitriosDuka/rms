package com.rms.rms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Currency {

    USD ("United States dollar"),
    EUR ("Euro"),
    JPY ("Japanese yen"),
    GBP ("Pound sterling"),
    AUD ("Australian dollar"),
    CAD ("Canadian dollar"),
    CHF ("Swiss franc"),
    HKD ("Hong Kong dollar"),
    NZD ("New Zealand dollar"),
    SEK ("Swedish krona"),
    KRW ("South Korean won"),
    SGD ("Singapore dollar"),
    NOK ("Norwegian krone"),
    MXN ("Mexican peso"),
    INR ("Indian rupee"),
    RUB ("Russian ruble"),
    ZAR ("South African rand"),
    TRY ("Turkish lira"),
    ALL ("Albanian lek");

    private final String currency;

}
