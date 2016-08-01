/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package at.gleb.sber.calculator;

import android.support.annotation.NonNull;

/**
 * Listens to user actions from the UI ({@link CalculatorView}), retrieves the data and updates the
 * UI as required.
 */
public class CalculatorPresenter implements CalculatorContract.Presenter {

//    private final TasksRepository mTasksRepository;

    private final CalculatorContract.View mCalculatorView;

//    private TasksFilterType mCurrentFiltering = TasksFilterType.ALL_TASKS;

    private boolean mFirstLoad = true;

    public CalculatorPresenter(@NonNull CalculatorContract.View calculatorView) {
//        mTasksRepository = checkNotNull(tasksRepository, "tasksRepository cannot be null");
        mCalculatorView = calculatorView;

        mCalculatorView.setPresenter(this);
    }

    @Override
    public void start() {
//        loadTasks(false);
    }

}
