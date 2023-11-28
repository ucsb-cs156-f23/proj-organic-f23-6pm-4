import React from 'react';
import SchoolsTable from 'main/components/School/SchoolsTable';
import { schoolsFixtures } from 'fixtures/schoolsFixtures';
import { currentUserFixtures } from 'fixtures/currentUserFixtures';
import { rest } from "msw";

export default {
    title: 'components/School/SchoolsTable',
    component: SchoolsTable
};

const Template = (args) => {
    return (
        <SchoolsTable {...args} />
    )
};

export const Empty = Template.bind({});

Empty.args = {
    schools: []
};

export const ThreeItemsOrdinaryUser = Template.bind({});

ThreeItemsOrdinaryUser.args = {
    schools: schoolsFixtures.threeSchools,
    currentUser: currentUserFixtures.userOnly,
};

export const ThreeItemsAdminUser = Template.bind({});
ThreeItemsAdminUser.args = {
    schools: schoolsFixtures.threeSchools,
    currentUser: currentUserFixtures.adminUser,
}

ThreeItemsAdminUser.parameters = {
    msw: [
        rest.delete('/api/schools', (req, res, ctx) => {
            window.alert("DELETE: " + JSON.stringify(req.url));
            return res(ctx.status(200),ctx.json({}));
        }),
    ]
};
