
package com.quartercode.qcutil;

import com.quartercode.qcutil.utility.ObjectUtil;

public class ProgressableGroup {

    protected Progressable progressableListener;

    public ProgressableGroup(final Progressable progressableListener) {

        this.progressableListener = progressableListener;
    }

    public Progressable generateAndAddProgressable() {

        return new Progressable() {

            @Override
            public void setProgressStatus(final String status) {

                progressableListener.setProgressStatus(status);
            }

            @Override
            public void setProgressPercent(final float percent) {

                progressableListener.setProgressPercent(percent);
            }
        };
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + (progressableListener == null ? 0 : progressableListener.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProgressableGroup other = (ProgressableGroup) obj;
        if (progressableListener == null) {
            if (other.progressableListener != null) {
                return false;
            }
        } else if (!progressableListener.equals(other.progressableListener)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return ObjectUtil.generateObjectStringWithNames(this, "progressableListener");
    }

}
