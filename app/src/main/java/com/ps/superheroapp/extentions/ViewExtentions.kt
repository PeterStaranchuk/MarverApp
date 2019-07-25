package com.ps.superheroapp.extentions

import android.view.View
import com.ps.superheroapp.SuperHeroApplication

fun View.getApp(): SuperHeroApplication = context.applicationContext as SuperHeroApplication
