package men.cbgg.gtdapp;

import android.os.StrictMode;
import android.util.Log;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import edu.ksu.canvas.CanvasApiFactory;
import edu.ksu.canvas.interfaces.AccountReader;
import edu.ksu.canvas.interfaces.AssignmentReader;
import edu.ksu.canvas.interfaces.CourseReader;
import edu.ksu.canvas.interfaces.UserReader;
import edu.ksu.canvas.model.Account;
import edu.ksu.canvas.model.Course;
import edu.ksu.canvas.model.assignment.Assignment;
import edu.ksu.canvas.oauth.NonRefreshableOauthToken;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.ListAccountOptions;
import edu.ksu.canvas.requestOptions.ListActiveCoursesInAccountOptions;
import edu.ksu.canvas.requestOptions.ListCourseAssignmentsOptions;
import edu.ksu.canvas.requestOptions.ListCurrentUserCoursesOptions;
import men.cbgg.gtdapp.ListViewRows.Task;
import men.cbgg.gtdapp.Storage.Storage;

public class CanvasConnection {

    public static OauthToken oauthToken;
    public static CanvasApiFactory apiFactory;

    public static void init() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String canvasBaseUrl = "https://canvas.ou.edu";
        oauthToken = new NonRefreshableOauthToken("SECRET_OAUTH_KEY");
        apiFactory = new CanvasApiFactory(canvasBaseUrl);
    }

    public static ArrayList<Course> getUserCourses() {
        CourseReader crs = apiFactory.getReader(CourseReader.class, oauthToken);
        ListCurrentUserCoursesOptions opts = new ListCurrentUserCoursesOptions();
        opts.withEnrollmentType(ListCurrentUserCoursesOptions.EnrollmentType.STUDENT);
        ArrayList<Course> returncourses = new ArrayList<Course>();
        try {
            ArrayList<Course> received = (ArrayList<Course>) crs.listCurrentUserCourses(opts);
            for (Course x : received) {
                if (x.getName() != null) {
                    returncourses.add(x);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return returncourses;
    }

    public static ArrayList<Assignment> getAssignments(String courseID) {



        AssignmentReader ar = apiFactory.getReader(AssignmentReader.class, oauthToken);
        ListCourseAssignmentsOptions opts = new ListCourseAssignmentsOptions(courseID);
        opts.bucketFilter(ListCourseAssignmentsOptions.Bucket.FUTURE);
        ArrayList<Assignment> returnassigns = new ArrayList<Assignment>();
        try {
            ArrayList<Assignment> assigns = (ArrayList<Assignment>) ar.listCourseAssignments(opts);
            for (Assignment x : assigns) {
                if (x.getName() != null) {
                    returnassigns.add(x);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnassigns;
    }

}
