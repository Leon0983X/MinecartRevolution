
package com.quartercode.qcutil.utility;

import com.quartercode.qcutil.Progressable;

public class ProgressUtil {

    public static Progressable prepareProgressable(Progressable progressable) {

        if (progressable == null) {
            return new Progressable() {

                @Override
                public void setProgressStatus(String status) {

                }

                @Override
                public void setProgressPercent(float percent) {

                }
            };
        } else {
            return progressable;
        }
    }

    private ProgressUtil() {

    }

}
