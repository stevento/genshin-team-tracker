package persistence;

import org.json.JSONObject;

// Copied from JsonSerializationDemo from UBC CPSC 210. Link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// ===================================================================
public interface Writable {
    JSONObject toJson();
}
