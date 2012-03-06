/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iloan.kernel;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 *
 * @author mrogers
 */
public class StatusHandlers
{

    public static String currentTask = "";
    public static JLabel taskLabel = new JLabel("");
    public static JProgressBar progressBar = new JProgressBar();

    public static void updateTaskLabel(String taskName)
    {
        taskLabel.setText(taskName);
    }

    public static void updateProgress(int min, int max, int value)
    {
        progressBar.setIndeterminate(false);
        progressBar.setMinimum(min);
        progressBar.setMaximum(max);
        progressBar.setValue(value);
    }

    public static void resetProgress()
    {
        progressBar.setIndeterminate(false);
        progressBar.setMinimum(0);
        progressBar.setMaximum(0);
        progressBar.setValue(0);
        taskLabel.setText("");
    }

    public static void setDone()
    {
        progressBar.setIndeterminate(false);
        progressBar.setMinimum(0);
        progressBar.setMaximum(0);
        progressBar.setValue(0);
        taskLabel.setText("Done");
        SwingWorker swingWorker = new SwingWorker()
        {
            @Override
            protected Object doInBackground() throws Exception
            {
                Thread.sleep(5000);
                taskLabel.setText("");
                return null;
            }
        };
        swingWorker.execute();
    }
}
