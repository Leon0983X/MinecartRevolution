
package com.quartercode.qcutil.utility;

import com.quartercode.qcutil.Progressable;

public class ProgressUtil {

    public static Progressable prepareProgressable(final Progressable progressable) {

        if (progressable == null) {
            return new Progressable() {

                @Override
                public void setProgressStatus(final String status) {

                }

                @Override
                public void setProgressPercent(final float percent) {

                }
            };
        } else {
            return progressable;
        }
    }

    private ProgressUtil() {

    }

}
