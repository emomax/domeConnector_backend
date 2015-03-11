package DomeUtils;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import java.util.Random;

//! Class for handling transformation matrices.
public class Transform {
    private double x, y, z;
    private double rotx, roty, rotz;
    
    // Stored for possible inter- or extrapolation
    private long timeStamp = 0;
    
    // Used for random generation
    private static Random rand = new Random();
    
    //! Constructor of transform. Input translation and rotation.
    public Transform(double _x, double _y, double _z, 
                     double _rotx, double _roty, double _rotz) {
        this.x = _x;
        this.y = _y;
        this.z = _z;
        
        this.rotx = _rotx;
        this.roty = _roty;
        this.rotz = _rotz;
    }
    
    // getters of everything
    public double getRotx() { return rotx; }
    public double getRoty() { return roty; }
    public double getRotz() { return rotz; }
    
    public double getX() { return rotx; }
    public double getY() { return roty; }
    public double getZ() { return rotz; }
    
    //! Setting the current timestamp for interpolation or extrapolation
    public void setTimeStamp(long timeStamp) {
            this.timeStamp = timeStamp;
    }
    
    //! Fetch data and populate Transform object with it.
    public static Transform fromSFSObject(ISFSObject data) {
        ISFSObject transformData = data.getSFSObject("transform");
        
        // set the variables from the data object
        double _x = transformData.getDouble("x");
        double _y = transformData.getDouble("y");
        double _z = transformData.getDouble("z");
        
        double _rx = transformData.getDouble("rx");
        double _ry = transformData.getDouble("ry");
        double _rz = transformData.getDouble("rz");
        
        // For interpolation / extrapolation purposes
        long timeStamp = transformData.getLong("t");
        
        // create a new transform and return it
        Transform transform = new Transform(_x, _y, _z, _rx, _ry, _rz);
        transform.setTimeStamp(timeStamp);
        return transform;
    }
    
    //! Prepare this Transform object for broadcasting.
    public void toSFSObject(ISFSObject data) {
        ISFSObject transformData = new SFSObject();
        transformData.putDouble("x", x);
        transformData.putDouble("y", y);
        transformData.putDouble("z", z);
        
        transformData.putDouble("rx", rotx);
        transformData.putDouble("ry", roty);
        transformData.putDouble("rz", rotz);
        
        transformData.putLong("t", this.timeStamp);
        
        data.putSFSObject("transform", transformData);
    }
}
