package com.drdisagree.iconify.ui.activity;

import static com.drdisagree.iconify.common.References.COLOR_ACCENT_PRIMARY;
import static com.drdisagree.iconify.common.References.COLOR_ACCENT_SECONDARY;
import static com.drdisagree.iconify.common.References.COLOR_PIXEL_DARK_BG;
import static com.drdisagree.iconify.common.References.FRAMEWORK_PACKAGE;
import static com.drdisagree.iconify.common.References.ICONIFY_COLOR_ACCENT_PRIMARY;
import static com.drdisagree.iconify.common.References.ICONIFY_COLOR_ACCENT_SECONDARY;
import static com.drdisagree.iconify.common.References.ICONIFY_COLOR_PIXEL_DARK_BG;
import static com.drdisagree.iconify.common.References.STR_NULL;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.drdisagree.iconify.R;
import com.drdisagree.iconify.config.Prefs;
import com.drdisagree.iconify.utils.FabricatedOverlayUtil;
import com.drdisagree.iconify.utils.OverlayUtil;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;
import java.util.Objects;

public class ColorEngine extends AppCompatActivity {

    public static List<String> FabricatedEnabledOverlays = FabricatedOverlayUtil.getEnabledOverlayList();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_engine);

        // Header
        CollapsingToolbarLayout collapsing_toolbar = findViewById(R.id.collapsing_toolbar);
        collapsing_toolbar.setTitle(getResources().getString(R.string.activity_title_color_engine));
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Basic colors
        LinearLayout basic_colors = findViewById(R.id.basic_colors);
        basic_colors.setOnClickListener(v -> {
            Intent intent = new Intent(ColorEngine.this, BasicColors.class);
            startActivity(intent);
        });

        // Monet engine
        LinearLayout monet_engine = findViewById(R.id.monet_engine);
        monet_engine.setOnClickListener(v -> {
            Intent intent = new Intent(ColorEngine.this, MonetEngine.class);
            startActivity(intent);
        });

        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch apply_minimal_qspanel = findViewById(R.id.apply_minimal_qspanel);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch apply_pitch_black_theme = findViewById(R.id.apply_pitch_black_theme);

        // Minimal QsPanel
        apply_minimal_qspanel.setChecked(Prefs.getBoolean("IconifyComponentQSST.overlay"));

        apply_minimal_qspanel.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                apply_pitch_black_theme.setChecked(false);
                OverlayUtil.disableOverlay("IconifyComponentQSPB.overlay");

                apply_minimal_qspanel.postDelayed(() -> {
                    OverlayUtil.enableOverlay("IconifyComponentQSST.overlay");
                }, 200);
            } else {
                OverlayUtil.disableOverlay("IconifyComponentQSST.overlay");
            }
        });

        // Pitch Black QsPanel
        apply_pitch_black_theme.setChecked(Prefs.getBoolean("IconifyComponentQSPB.overlay"));

        apply_pitch_black_theme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                apply_minimal_qspanel.setChecked(false);
                OverlayUtil.disableOverlay("IconifyComponentQSST.overlay");

                apply_pitch_black_theme.postDelayed(() -> {
                    OverlayUtil.enableOverlay("IconifyComponentQSPB.overlay");
                }, 200);
            } else {
                OverlayUtil.disableOverlay("IconifyComponentQSPB.overlay");
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}