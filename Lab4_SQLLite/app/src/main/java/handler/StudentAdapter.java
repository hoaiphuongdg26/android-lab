package handler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4_sqllite.MainActivity;
import com.example.lab4_sqllite.R;

import java.util.ArrayList;

import model.Student;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder>{
    ArrayList<Student> ArrayStudent;
    MainActivity context;

    private  onItemClickListener itemListener;

    public interface  onItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener)
    {
        itemListener = listener;
    }
    public StudentAdapter(ArrayList<Student> arrayStudent, MainActivity context) {
        this.ArrayStudent = arrayStudent;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_student, parent, false);
        return new ViewHolder(itemView, itemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item_FullName.setText(ArrayStudent.get(position).getFullName());
        holder.studentID.setText(ArrayStudent.get(position).getStudentId());
        Student student = ArrayStudent.get(position);


        holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogUpdate(student.getFullName(), student.getId());
            }
        });
        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogDelete(student.getStudentId(), student.getFullName(), student.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return ArrayStudent.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_FullName;
        TextView studentID;
        ImageView img_edit, img_delete;

        public ViewHolder(@NonNull View itemView, onItemClickListener listener) {
            super(itemView);
            item_FullName = itemView.findViewById(R.id.name);
            studentID = itemView.findViewById(R.id.studentId);
            img_edit = itemView.findViewById(R.id.img_edit);
            img_delete = itemView.findViewById(R.id.img_delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null)
                    {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}

