package domain;

import com.mybank.domain.Bank;
import com.mybank.domain.CheckingAccount;
import com.mybank.domain.Customer;
import com.mybank.domain.SavingsAccount;
import com.mybank.data.DataSource;
import com.mybank.domain.Account;

public class CustomerReportHtml {

  public CustomerReportHtml() {
  }

  public String generateHtmlReport() {
    StringBuilder html = new StringBuilder();

    html.append("<html>\n");
    html.append("<head><title>Customers Report</title></head>\n");
    html.append("<body>\n");
    html.append("<h1>CUSTOMERS REPORT</h1>\n");

    // For each customer...
    for (int cust_idx = 0; cust_idx < Bank.getNumberOfCustomers(); cust_idx++) {
      Customer customer = Bank.getCustomer(cust_idx);

      // Customer name
      html.append("<h2>Customer: ")
          .append(customer.getLastName()).append(", ")
          .append(customer.getFirstName()).append("</h2>\n");

      // List of accounts
      html.append("<ul>\n");

      for (int acct_idx = 0; acct_idx < customer.getNumberOfAccounts(); acct_idx++) {
        Account account = customer.getAccount(acct_idx);
        String account_type;

        // Determine the account type
        if (account instanceof SavingsAccount) {
          account_type = "Savings Account";
        } else if (account instanceof CheckingAccount) {
          account_type = "Checking Account";
        } else {
          account_type = "Unknown Account Type";
        }

        html.append("<li>")
            .append(account_type)
            .append(": current balance is ")
            .append(account.getBalance())
            .append("</li>\n");
      }

      html.append("</ul>\n");
    }

    html.append("</body>\n");
    html.append("</html>");

    return html.toString();
  }
}
