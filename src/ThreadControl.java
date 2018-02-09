
    public class ThreadControl {
        Boolean flag;

        public ThreadControl(Boolean flag) {
            this.flag = flag;
        }

        public Boolean getFlag() {
            return flag;
        }

        public void setFlag(Boolean flag) {
            System.out.println("flag was changed");
            this.flag = flag;
        }
    }

