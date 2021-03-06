import com.example.alek.task.R;
import com.example.alek.task.BR;
import com.stfalcon.androidmvvmhelper.mvvm.activities.BindingActivity;
import com.example.alek.task.databinding.ActivityTestBinding;


/**
 * Created by alek on 08/09/2017.
 */

public class TestActivity extends BindingActivity<ActivityTestBinding, TestActivityVM> {

    @Override
    public TestActivityVM onCreate() {
        return new TestActivityVM(this);
    }

    @Override
    public int getVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

}