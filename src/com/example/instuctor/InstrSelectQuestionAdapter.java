package com.example.instuctor;


import java.util.List;

import com.example.rewise.Question;
import com.example.rewise.R;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class InstrSelectQuestionAdapter extends ArrayAdapter<Question> {

  private final List<Question> list;
  private final Activity context;

  public InstrSelectQuestionAdapter(Activity context, List<Question> list) {
    super(context, R.layout.list_item_course_selection, list);
    this.context = context;
    this.list = list;
  }

  static class ViewHolder {
    protected TextView text;
    protected CheckBox checkbox;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View view = null;
    if (convertView == null) {
      LayoutInflater inflator = context.getLayoutInflater();
      view = inflator.inflate(R.layout.list_item_question_selection, null);
      final ViewHolder viewHolder = new ViewHolder();
      viewHolder.text = (TextView) view.findViewById(R.id.label);
      viewHolder.checkbox = (CheckBox) view.findViewById(R.id.check);
      viewHolder.checkbox
          .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                boolean isChecked) {
                Question element = (Question) viewHolder.checkbox
                  .getTag();
                //Mapping Questions to Current Quiz which called this activity.
                element.setSelected(buttonView.isChecked());
            }
          });
      view.setTag(viewHolder);
      viewHolder.checkbox.setTag(list.get(position));
    } else {
      view = convertView;
      ((ViewHolder) view.getTag()).checkbox.setTag(list.get(position));
    }
    ViewHolder holder = (ViewHolder) view.getTag();
    holder.text.setText(list.get(position).getQuestion());
    //Mapping Questions to Current Quiz which called this activity.
    holder.checkbox.setChecked(list.get(position).isSelected());
    return view;
  }
} 