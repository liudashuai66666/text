package client.tool;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
//实现发送验证码倒计时
public class Clock extends Pane {
    private Timeline animation;
    private String S = "";
    private int tmp = 61;
    Label label;
    public Clock(Label timer) {
        this.label=timer;
        animation = new Timeline(new KeyFrame(Duration.millis(1000), e -> timelabel()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }
    public Clock timelabel() {
        tmp--;
        S = tmp +"s";
        label.setText(S);
        if(tmp==0){
            label.setText("");
            animation.stop();
        }
        return null;
    }
}
