package test;

public class CountdownTimer {
    public static void main(String[] args) {
        int secondsRemaining = 5 * 60;

        while (secondsRemaining >= 0) {
            // 计算剩余分钟和秒数
            int minutes = secondsRemaining / 60;
            int seconds = secondsRemaining % 60;

            // 构造倒计时消息
            String countdownMessage = String.format("\r在%d分%d秒后可以重新输入密码！", minutes, seconds);

            // 输出倒计时消息到控制台
            System.out.print(countdownMessage);

            try {
                // 线程休眠1秒钟
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 减少剩余时间
            secondsRemaining--;
        }

        // 倒计时结束后换行
        System.out.println("\n倒计时结束，可以重新输入密码！");
    }
}
