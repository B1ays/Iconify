package com.drdisagree.iconify.installer;

import com.drdisagree.iconify.Iconify;
import com.drdisagree.iconify.config.PrefConfig;
import com.drdisagree.iconify.services.ApplyOnBoot;
import com.topjohnwu.superuser.Shell;

import java.io.File;

public class NotifInstaller {

    private static final int TOTAL_NOTIFICATIONS = 10;

    public static void install_pack(int n) {
        disable_others(n);
        enable_pack(n);
        PrefConfig.savePrefBool(Iconify.getAppContext(), "customColor", true);
        ApplyOnBoot.applyColors();
        PrefConfig.savePrefBool(Iconify.getAppContext(), "fabricatedcornerRadius", true);
        ApplyOnBoot.applyCornerRadius();
    }

    protected static void enable_pack(int n) {

        String path = "/system/product/overlay/IconifyComponentNF" + n + ".apk";

        if (new File(path).exists()) {

            String overlay = (path.replaceAll("/system/product/overlay/", "")).replaceAll("apk", "overlay");

            try {
                Shell.cmd("cmd overlay enable --user current " + overlay).exec();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    public static void disable_pack(int n) {

        String path = "/system/product/overlay/IconifyComponentNF" + n + ".apk";

        if (new File(path).exists()) {

            String overlay = (path.replaceAll("/system/product/overlay/", "")).replaceAll("apk", "overlay");

            try {
                Shell.cmd("cmd overlay disable --user current " + overlay).exec();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    protected static void disable_others(int n) {

        for (int i = 1; i <= TOTAL_NOTIFICATIONS; i++) {
            if (i != n) {
                String path = "/system/product/overlay/IconifyComponentNF" + i + ".apk";

                if (new File(path).exists()) {

                    String overlay = (path.replaceAll("/system/product/overlay/", "")).replaceAll("apk", "overlay");

                    try {
                        Shell.cmd("cmd overlay disable --user current " + overlay).exec();
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
            }
        }
    }
}