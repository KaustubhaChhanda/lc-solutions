class Solution {
    public double angleClock(int hour, int minutes) {
        double hourHand = (hour % 12) + minutes / 60.0;
        double minuteHand = minutes / 5.0;

        double angle = Math.abs(hourHand - minuteHand) * 30;

        return (angle > 180) ? 360 - angle : angle;
    }
}