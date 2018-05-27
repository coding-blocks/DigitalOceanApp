package in.tosc.doandroidlib.objects;

/**
 * Created by championswimmer on 14/07/17.
 */
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Wrapper over Account
 *
 * @author championswimmer
 */
public class AccountInfo extends RateLimitBase {
    private static final long serialVersionUID = 5951525501167424430L;

    private Account account;

    /**
     *
     * @return account object
     */
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
