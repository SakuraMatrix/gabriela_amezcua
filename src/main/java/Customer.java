import java.time.Instant;

public class Customer {
        // State
        private int SS;
        private String customer_Fname;
        private String customer_Lnname;
        private int customer_AcctNo;
        //private Instant customer_DOB;

        // Constructor
        public Customer (int SS, String customer_Fname, String customer_Lnname,
                    int customer_AcctNo){
            this.SS = SS;
            this.customer_Fname = customer_Fname;
            this.customer_Lnname = customer_Lnname;
            this.customer_AcctNo = customer_AcctNo;
           // this.customer_DOB = customer_DOB;
        }

        // Getters and Setters
        public int getSS() {
            return SS;
        }

        public void setSS(int SS) {
            this.SS = SS;
        }

        public String getCustomer_Fname() {
            return customer_Fname;
        }

        public void setCustomer_Fname(String customer_Fname) {
            this.customer_Fname = customer_Fname;
        }

        public String getCustomer_Lnname() {
            return customer_Lnname;
        }

        public void setCustomer_Lnname(String customer_Lnname) {
            this.customer_Lnname = customer_Lnname;
        }

        public int getCustomer_AcctNo() {
            return customer_AcctNo;
        }

        public void setCustomer_AcctNo(int customer_AcctNo) {
            this.customer_AcctNo = customer_AcctNo;
        }

//        public Instant getCustomer_DOB() {
//            return customer_DOB;
//        }
//
//        public void setCustomer_DOB(Instant customer_DOB) {
//            this.customer_DOB = customer_DOB;
//        }

}
