package System;


import GUI.Entities.View;

import java.io.File;

public interface GUISystemS {
    void analyseSource(File file);
    void selectCircle(String circleIdentifier);
    void addView (View view);

}
