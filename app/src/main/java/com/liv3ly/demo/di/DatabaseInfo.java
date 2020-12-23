/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by HoangDH 23/12/2020.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface DatabaseInfo {
}
