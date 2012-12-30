
package com.quartercode.qcutil.utility;

public class OperatingSystemUtil {

    public enum OperatingSystem {

        WINDOWS, OS_X, LINUX_UNIX, SOLARIS, OTHER;
    }

    public enum BitArchitecture {

        EIGHT_BIT (8), SIXTEEN_BIT (16), THIRTYTWO_BIT (32), SIXTYFOUR_BIT (64), OTHER (-1);

        public final int bits;

        BitArchitecture(final int bits) {

            this.bits = bits;
        }

        @Override
        public String toString() {

            return String.valueOf(bits);
        }
    }

    public static String getPlatformName() {

        return System.getProperty("os.name");
    }

    public static OperatingSystem getPlatform() {

        final String platformName = getPlatformName();

        if (platformName.toLowerCase().contains("win")) {
            return OperatingSystem.WINDOWS;
        } else if (platformName.toLowerCase().contains("mac") || platformName.toLowerCase().contains("darwin")) {
            return OperatingSystem.OS_X;
        } else if (platformName.toLowerCase().contains("linux") || platformName.toLowerCase().contains("unix")) {
            return OperatingSystem.LINUX_UNIX;
        } else if (platformName.toLowerCase().contains("solaris") || platformName.toLowerCase().contains("sunos")) {
            return OperatingSystem.SOLARIS;
        } else {
            return OperatingSystem.OTHER;
        }
    }

    public static String getArchitectureName() throws NumberFormatException {

        return System.getProperty("os.arch");
    }

    public static int getBitArchitectureBits() throws NumberFormatException {

        return Integer.parseInt(System.getProperty("sun.arch.data.model"));
    }

    public static BitArchitecture getBitArchitecture() throws NumberFormatException {

        final int bits = getBitArchitectureBits();
        for (final BitArchitecture architecture : BitArchitecture.values()) {
            if (bits == architecture.bits) {
                return architecture;
            }
        }

        return BitArchitecture.OTHER;
    }

    private OperatingSystemUtil() {

    }

}
